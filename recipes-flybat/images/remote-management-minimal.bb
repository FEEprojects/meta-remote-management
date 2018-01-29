# Base this image on core-image-minimal
# include recipes-core/images/core-image-minimal.bb

include recipes-core/images/rpi-basic-image.bb

# IMAGE_INSTALL += "cockpit-bridge cockpit-ws cockpit-dashboard"
IMAGE_INSTALL += "cockpit-ws cockpit-dashboard cockpit-bridge cockpit-packages"
IMAGE_INSTALL_append=" openvpn autoconf cronie nano python-smbus python-pip i2c-tools vim gnupg docker cockpit-ws cockpit-dashboard cockpit-bridge cockpit-packages "
IMAGE_INSTALL_append=" fixruntimehack network-vpn-setup connman connman-client connman-wait-online enable-i2c-dev "
IMAGE_INSTALL_append="  linux-firmware-bcm43430"
IMAGE_INSTALL_append=" connman-vpn connman-plugin-vpn-openvpn"

# enable vpn for connman
PACKAGECONFIG_append_pn-connman = " openvpn vpnc l2tp pptp"
