SUMMARY="Adds network and VPN setup scripts"
DESCRIPTION="Adds a network systemd script that initialises the VPN and network on boot"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "\
        file://init.sh \
        file://network-vpn-setup.service \
"
S = "${WORKDIR}"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"

SYSTEMD_SERVICE_${PN} = " network-vpn-setup.service"


FILES_${PN} += " network-vpn-setup.service \
                 /usr/network-vpn-setup/init.sh \
"

do_install() {

    install -d ${D}${systemd_system_unitdir}
    install -d ${D}/usr/network-vpn-setup
    install -m 0755 ${WORKDIR}/network-vpn-setup.service ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/init.sh ${D}/usr/network-vpn-setup

}
