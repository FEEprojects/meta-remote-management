LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://data.tar.xz.yo"

# inherit bin_package

INSANE_SKIP_${PN} += "already-stripped arch"

DEPENDS = "glib-2.0 json-glib polkit polkit glib-networking"

do_install() {

    [ -d "${S}" ] || exit 1
    cd ${S} || exit 1

    tar xvf ${WORKDIR}/data.tar.xz.yo --directory .

    tar --no-same-owner --exclude='./patches' --exclude='./.pc' -cpf - .         | tar --no-same-owner -xpf - -C ${D}

}

FILES_${PN} = "/"
