LICENSE = "MIT"

# Cockpit
# IMAGE_INSTALL += "cockpit-bridge cockpit-ws cockpit-dashboard"
IMAGE_INSTALL_append=" cockpit-ws cockpit-dashboard cockpit-bridge cockpit-packages "

# azure stuff
#IMAGE_INSTALL_append=" azure-iot-edge azure-iot-sdk python-azure-iot-sdk python-azure-cli"

# python things
IMAGE_INSTALL_append=" python3-spidev python3 python3-dev python3-distutils python3-pip python3-dev python3-distutils "
IMAGE_INSTALL_append=" python-cryptography python python-setuptools python-dev python-smbus python-pip python-distutils "

IMAGE_INSTALL_append=" libffi-dev python-cffi "

# utilities
IMAGE_INSTALL_append= " screen vim nano minicom cronie "
IMAGE_INSTALL_append= " pam-ssh-agent-auth "

# make tools
IMAGE_INSTALL_append=" gcc make autoconf "

# linux utils
IMAGE_INSTALL_append=" sudo "

# docker
IMAGE_INSTALL_append=" docker "

# sensor/hardware-related
IMAGE_INSTALL_append=" i2c-tools "

# openvpn
IMAGE_INSTALL_append=" openvpn "

# nodejs
IMAGE_INSTALL_append=" nodejs "

# connman
IMAGE_INSTALL_append=" connman connman-client connman-wait-online connman-vpn connman-plugin-vpn-openvpn "
# enable vpn for connman
PACKAGECONFIG_append_pn-connman = " openvpn vpnc l2tp pptp"

# configure login credentials
# See variables AUTHKEYCFG_USERNAME and AUTHKEYCFG_GITHUB_USERNAMES to configure
# how this runs. Change the values in local.conf.
IMAGE_INSTALL_append=" add-ssh-key "

# dtc tools
IMAGE_INSTALL_append=" dtc "

# network-vpn-setup (not working, systemd based openvpn --config stuff)
# IMAGE_INSTALL_append=" network-vpn-setup "
#MACHINE_FEATURES_remove = "bluetooth"
