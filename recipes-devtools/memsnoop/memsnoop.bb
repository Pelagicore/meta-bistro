#
#   Copyright (C) 2017 Pelagicore AB
#

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/memsnoop.c;md5=e9b4cbdfa8db5882746a4349e74c119f"

SRC_URI = "file://memsnoop.c"

do_compile() {
        $CC ${WORKDIR}/memsnoop.c -o memsnoop ${LDFLAGS}
}

do_install() {
        install -d ${D}/${bindir}
        install -m 755 ${B}/memsnoop ${D}/${bindir}
}

FILES_${PN} = "${bindir}/memsnoop"
