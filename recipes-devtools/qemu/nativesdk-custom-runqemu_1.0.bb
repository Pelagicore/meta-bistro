#
#   Copyright (C) 2015 Pelagicore AB
#
SUMMARY = "Customized runqemu script"
LICENSE = "GPLv2"

RDEPENDS_${PN} = "nativesdk-qemu"

PR = "r0"

LIC_FILES_CHKSUM = "file://${BISTROBASE}/scripts/custom-runqemu;endline=18;md5=5e7f958019d32f4196ff25212d73caeb"

SRC_URI = "file://${BISTROBASE}/scripts/custom-runqemu \
           file://${BISTROBASE}/scripts/custom-runqemu-internal \
          "
S = "${WORKDIR}"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}${BISTROBASE}/scripts/custom-runqemu* ${D}${bindir}/
}

inherit nativesdk
