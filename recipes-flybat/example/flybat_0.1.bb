#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

SUMMARY = "Simple helloworld application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"



python do_printdate () {
    import time
    # print(time.strftime('%Y%m%d', time.gmtime()))

    bb.plain("%s  %s" % ("batman nananannana",  time.strftime('%Y%m%d', time.gmtime())))
}
addtask printdate

SRC_URI = "file://helloworld.c"

S = "${WORKDIR}"

do_compile() {

        bbplain working dir `pwd`
        bbplain working dir listing `ls -lsa`

	     ${CC} ${LDFLAGS} helloworld.c -o yobatman
}

do_install() {
	    #  install -d ${D}${bindir}
	    #  install -m 0755 helloworld ${D}${bindir}

        bbplain working dir `pwd`
        bbplain working dir listing `ls -lsa`


	    install -d ${D}${bindir}
		install -m 0755 yobatman ${D}${bindir}
}
