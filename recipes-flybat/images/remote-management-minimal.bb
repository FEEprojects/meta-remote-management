# Base this image on core-image-minimal
# include recipes-core/images/core-image-minimal.bb

include recipes-core/images/rpi-basic-image.bb

# IMAGE_INSTALL += "cockpit-bridge cockpit-ws cockpit-dashboard"
IMAGE_INSTALL += "cockpit-ws cockpit-dashboard cockpit-bridge cockpit-packages"
IMAGE_INSTALL_append=" openvpn autoconf cronie nano python-smbus python-pip i2c-tools vim gnupg docker cockpit-ws cockpit-dashboard cockpit-bridge cockpit-packages "
IMAGE_INSTALL_append=" fixruntimehack network-vpn-setup connman connman-wait-online enable-i2c-dev"
