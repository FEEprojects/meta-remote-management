SUMMARY="Adds network and VPN setup scripts"
DESCRIPTION="Adds a network systemd script that initialises the VPN and network on boot"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "\
    file://eth0watch.sh \
    file://network-eth0-watcher.service \
    file://vpninit.sh \
    file://network-vpn-setup.service \
"
S = "${WORKDIR}"

inherit systemd

SYSTEMD_PACKAGES = "network-vpn-setup network-eth0-watcher"

PACKAGES = "network-vpn-setup network-eth0-watcher"

SYSTEMD_SERVICE_network-vpn-setup = " network-vpn-setup.service"
SYSTEMD_SERVICE_network-eth0-watcher = " network-eth0-watcher.service"

# FILES_${PN} += " ${systemd_system_unitdir}/network-vpn-setup.service \
#                  /usr/network-setup-service/vpninit.sh \
#                  ${systemd_system_unitdir}/network-eth0-watcher.service \
#                  /usr/network-setup-service/eth0watch.sh \
# "

FILES_network-vpn-setup = " ${systemd_system_unitdir}/network-vpn-setup.service \
                 /usr/network-setup-service/vpninit.sh \
"

FILES_network-eth0-watcher = " ${systemd_system_unitdir}/network-eth0-watcher.service \
                 /usr/network-setup-service/eth0watch.sh \
"

do_install() {

    install -d ${D}${systemd_system_unitdir}
    install -d ${D}/usr/network-setup-service
    install -m 0755 ${WORKDIR}/network-vpn-setup.service ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/vpninit.sh ${D}/usr/network-setup-service

    install -m 0755 ${WORKDIR}/network-eth0-watcher.service ${D}${systemd_system_unitdir}
    install -m 0755 ${WORKDIR}/eth0watch.sh ${D}/usr/network-setup-service

}
