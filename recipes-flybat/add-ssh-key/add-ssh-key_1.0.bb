# Some documentation about this package
#
# It creates a new user, and adds a bunch of public keys in ~/.ssh/authorized_keys
# of that user.
#
# Configure the username by setting:
#   AUTHKEYCFG_USERNAME="superman"
#
#       # (or whatever else you want)
# in local.conf.
#
# Configure what SSH public keys are added by:
#
# 1. simply appending them to the authorized_keys file in this package, located
# at:
#
#    meta-remote-management/recipes-flybat/add-ssh-key/add-ssh-key-1.0/files/authorized_keys
#
# 2. by specifying the AUTHKEYCFG_GITHUB_USERNAMES variable in local.conf. By
# default it's empty. Set it to be a space separated list of github usernames. This
# package will download all the public keys for those usernames and put them in
# authorized_keys of the newly created user. (doing this by downloading
# https://github.com/<username>.keys for all users in the list).
#
# It also adds that user to the ${COMMON_GROUP_NAME} group. The group is created
# by this package too.

SUMMARY="Copy authorized_keys file"
DESCRIPTION="Add a fixed SSH key to the image, the key is in add-ssh-key-1.0 folder"

# Note for the future:
# - it might be nicer to use the github usernames as usernames and create one user
# for each github username, as opposed to a generic user (now it's called "user")
# - it would also make things more confiusing while running things, permission
# problems at runtime, etc... (e.g. create a folder, put a file in it, and someone
# else in the group won't have access to write there by default, and this is just
# the obvious... so the one user solution makes this easy for now)

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "\
        file://authorized_keys \
"

AUTHKEYCFG_DEFAULT_PASSWORD ??= "penguin"
AUTHKEYCFG_USERNAME ??= "root"
AUTHKEYCFG_GITHUB_USERNAMES ??= ""

PACKAGE_ARCHS[vardeps] = " AUTHKEYCFG_USERNAME AUTHKEYCFG_GITHUB_USERNAMES "

inherit useradd

USERADD_PACKAGES = "${PN}"
# airquality group created here!
GROUPADD_PARAM_${PN} = " ${COMMON_GROUP_NAME} "
USERADD_PARAM_${PN} = " -P ${AUTHKEYCFG_DEFAULT_PASSWORD} -u 1200 -d /home/${AUTHKEYCFG_USERNAME} -r -s /bin/bash -U -G sudo,${COMMON_GROUP_NAME}  ${AUTHKEYCFG_USERNAME};"

do_downloadkeys() {

    bbplain "fetching extra keys"
    bbplain "AUTHKEYCFG_GITHUB_USERNAMES: ${AUTHKEYCFG_GITHUB_USERNAMES}"
    bbplain "AUTHKEYCFG_USERNAME: ${AUTHKEYCFG_USERNAME}"

    cp ${WORKDIR}/authorized_keys ${WORKDIR}/final_authorized_keys

    # if there are some github usernames, then download them
    if [ ! -z "${AUTHKEYCFG_GITHUB_USERNAMES}" ]
    then
        for ghusr in ${AUTHKEYCFG_GITHUB_USERNAMES}
        do
            echo "#\n# Keys for ${ghusr}\n#" >> ${WORKDIR}/final_authorized_keys
            bbplain "Downloading keys for ${ghusr}..."
            /usr/bin/curl --fail https://github.com/${ghusr}.keys >> ${WORKDIR}/final_authorized_keys
            if [ ! $? -eq 0 ]
            then
                bberror "GitHub keys request failed for username ${ghusr}. aborting."
                exit 1
            fi
        done
    fi
}

do_install() {
    [ -d ${D}/home/${AUTHKEYCFG_USERNAME}/.ssh ] || install -d ${D}/home/${AUTHKEYCFG_USERNAME}/.ssh
    install -m 0644 ${WORKDIR}/final_authorized_keys ${D}/home/${AUTHKEYCFG_USERNAME}/.ssh/authorized_keys
    chown -R ${AUTHKEYCFG_USERNAME} ${D}/home/${AUTHKEYCFG_USERNAME}/.ssh
    chgrp -R ${AUTHKEYCFG_USERNAME} ${D}/home/${AUTHKEYCFG_USERNAME}/.ssh
}

addtask do_downloadkeys after do_fetch before do_install

FILES_${PN}=" /home/${AUTHKEYCFG_USERNAME}/.ssh/authorized_keys "
