# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   usr/share/doc/cockpit-bridge/copyright
#
# LICENSE = "LGPLv2.1"
# LIC_FILES_CHKSUM = "file://tools/debian/copyright.template;md5=62230c0032c15b542b5682f99168b2a0"

# #### How this package was made ####
#
# Following the steps:
#
# git clone git@github.com:cockpit-project/cockpit
# cd cockpit
# git checkout 148 # have the same version to avoid problems
# npm install
# # if needed:
# # [sudo] npm install -g webpack
# webpack
# mkdir -p usr/share/cockpit/
# cp -r dist/* usr/share/cockpit/
# tar zcvf data.tar.gz usr
#
# Then just rename data.tar.gz to data.tar.gz.yo and that's how I got data.tar.gz.yo for this package.



LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "file://data.tar.gz.yo"

# inherit bin_package

INSANE_SKIP_${PN} += "already-stripped arch"

DEPENDS = "cockpit-ws cockpit-dashboard udisks2"

# S = "${WORKDIR}"

do_install() {


    bbplain installing a random package to see if it conflicts

    # mkdir -p ${D}/usr/share/cockpit/ssh
    # echo "{}" > ${D}/usr/share/cockpit/ssh/manifest.json

    bbplain working dir listing `ls -lsa`
    # echo "success yo" > ${D}/status

    bbplain working dir pwd     `pwd`
    bbplain working dir WORKDIR ${WORKDIR}
    bbplain working dir S       ${S}
    # rm data.tar.xz.c

    bbplain running the crazy bit
    [ -d "${S}" ] || exit 1
    cd ${S} || exit 1

    tar xvf ${WORKDIR}/data.tar.gz.yo --directory .

    bbplain current dir $(pwd): $(ls)

    mkdir -p ${D}/usr/share/cockpit/

    cd usr/share/cockpit

    tar --no-same-owner --exclude='./patches' --exclude='./.pc' -cf - . \
        | tar --no-same-owner -xf - -C ${D}/usr/share/cockpit/

    bbplain fnished running crazy stuff

    bbplain DISTRO_FEATURES ${DISTRO_FEATURES}
}

FILES_${PN} = "/usr/share/cockpit/*"


pkg_postinst_${PN}() {
    bbplain post install
    echo "post install"
    exit 0
}

# S = "${WORKDIR}"
#
# do_install() {
#     bbplain installing cockpit-bridge into D: ${D}
#
#
#     bbplain working dir `pwd`
#     bbplain working dir listing `ls -lsa`
#
#     # tar xvf data.tar.xz --directory ${D}
#
#
#
# }
