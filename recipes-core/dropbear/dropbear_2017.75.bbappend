
do_install_append() {
    echo 'DROPBEAR_EXTRA_ARGS="-s -g"' > ${D}${sysconfdir}/default/dropbear
}
