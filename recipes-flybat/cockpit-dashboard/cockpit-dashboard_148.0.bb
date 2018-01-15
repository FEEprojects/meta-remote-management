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

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


# No information for SRC_URI yet (only an external source tree was specified)
SRC_URI = "file://data.tar.xz.yo"

# inherit bin_package

INSANE_SKIP_${PN} += "already-stripped arch"

DEPENDS = "glib-2.0 json-glib libssh systemd cockpit-ws"

# S = "${WORKDIR}"

do_install() {


    bbplain working dir listing `ls -lsa`
    echo "success yo" > ${D}/status

    bbplain working dir pwd     `pwd`
    bbplain working dir WORKDIR ${WORKDIR}
    bbplain working dir S       ${S}
    # rm data.tar.xz.c

    bbplain running the crazy bit
    [ -d "${S}" ] || exit 1
    cd ${S} || exit 1

    tar xvf ${WORKDIR}/data.tar.xz.yo --directory .

    tar --no-same-owner --exclude='./patches' --exclude='./.pc' -cpf - . \
        | tar --no-same-owner -xpf - -C ${D}

    bbplain fnished running crazy stuff

    bbplain DISTRO_FEATURES ${DISTRO_FEATURES}
}

FILES_${PN} = "/ /usr/share/cockpit/*"


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
