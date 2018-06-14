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

do_install() {

    [ -d "${S}" ] || exit 1
    cd ${S} || exit 1

    tar xvf ${WORKDIR}/data.tar.gz.yo --directory .

    mkdir -p ${D}/usr/share/cockpit/

    cd usr/share/cockpit

    tar --no-same-owner --exclude='./patches' --exclude='./.pc' -cf - . \
        | tar --no-same-owner -xf - -C ${D}/usr/share/cockpit/

}

# possible fix for conflicts with dashboard and others
DIRFILES = "1"


FILES_${PN} = "/usr/share/cockpit/*"
