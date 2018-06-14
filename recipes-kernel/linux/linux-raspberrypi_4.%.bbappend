FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi-4.%:"

SRC_URI_append = " file://0001-added-spi-gpio-cs-overlay.dts.patch "
SRC_URI_append = " file://0002-add-enable-spi-overlay.patch "
SRC_URI_append = " file://0003-enable-i2c-and-spi-by-default-patch.patch "
