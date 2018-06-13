SUMMARY="Keeps openvpn up"
DESCRIPTION="Adds a file in /etc/modules-load.d/ to enable i2c-dev"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "\
        file://vpn-up.sh \
        file://connect.sh \
"

do_install() {


    install -d ${D}/home/root/
    install -m 0644 ${WORKDIR}/vpn-up.sh ${D}/home/root/
    install -m 0644 ${WORKDIR}/connect.sh ${D}/home/root/
}

FILES_${PN}="/home/root/vpn-up.sh /home/root/connect.sh"
# FILES_${PN}_append=""
