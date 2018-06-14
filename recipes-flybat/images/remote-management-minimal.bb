# Base this image on core-image-minimal
#include recipes-core/images/core-image-minimal.bb

include recipes-core/images/rpi-basic-image.bb

# Create a wic image in order to add a new partition
#IMAGE_FSTYPES_append = "wic"
#WKS_FILE = "sdimage-sota.wks"



# IMAGE_INSTALL += "cockpit-bridge cockpit-ws cockpit-dashboard"
IMAGE_INSTALL += " cockpit-ws cockpit-dashboard cockpit-bridge cockpit-packages"
IMAGE_INSTALL += " pam-ssh-agent-auth "
#IMAGE_INSTALL_append=" azure-iot-edge azure-iot-sdk python-azure-iot-sdk python-azure-cli"
IMAGE_INSTALL_append=" python3-spidev screen docker python-cryptography python-cffi gcc make minicom openvpn autoconf cronie nano python3 python3-dev python3-distutils python3-pip python3-dev python3-distutils python python-setuptools python-dev nodejs python-smbus python-pip libffi-dev python-distutils i2c-tools vim"
IMAGE_INSTALL_append=" fixruntimehack connman connman-client connman-wait-online enable-i2c-dev restart-vpn-dev "
IMAGE_INSTALL_append="  linux-firmware-bcm43430"
IMAGE_INSTALL_append=" connman-vpn connman-plugin-vpn-openvpn"

IMAGE_INSTALL_append=" dtc "

# network-vpn-setup (not working, systemd based openvpn --config stuff)
# IMAGE_INSTALL_append=" network-vpn-setup "
#MACHINE_FEATURES_remove = "bluetooth"

# enable vpn for connman
PACKAGECONFIG_append_pn-connman = " openvpn vpnc l2tp pptp"
