SUMMARY="Enable i2c-dev so there's no need to modprobe i2c-dev on boot with systemd"
DESCRIPTION="Adds a file in /etc/modules-load.d/ to enable i2c-dev"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "\
        file://i2c-dev.conf \
"

do_install() {

    # this is required so the docker package will run
    install -d ${D}/etc/modules-load.d
    install -m 0644 ${WORKDIR}/i2c-dev.conf ${D}/etc/modules-load.d

}

FILES_${PN}="/etc/modules-load.d/i2c-dev.conf"
