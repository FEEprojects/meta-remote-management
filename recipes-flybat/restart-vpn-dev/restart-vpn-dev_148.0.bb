SUMMARY="Keeps openvpn up"
DESCRIPTION="Adds a file in /etc/modules-load.d/ to enable i2c-dev"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "\
        file://vpn-up.sh \
        file://connect.sh \
"

do_install() {
    install -d ${D}/etc/vpn-enable-scripts
    install -m 0755 ${WORKDIR}/vpn-up.sh ${D}/etc/vpn-enable-scripts/
    install -m 0755 ${WORKDIR}/connect.sh ${D}/etc/vpn-enable-scripts/
}

FILES_${PN}="/etc/vpn-enable-scripts/vpn-up.sh /etc/vpn-enable-scripts/connect.sh"
# FILES_${PN}_append=""

RDEPENDS_restart-vpn-dev="bash"
