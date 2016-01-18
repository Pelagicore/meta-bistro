#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "Vala vapi files for gssdp, gupnp and gupnp-av"
SECTION = "multimedia"
LICENSE = "LGPLv2"
SRC_URI = "file://vapi-files.tar.bz2"
S = "${WORKDIR}/vapi-files/"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
	mkdir -p ${D}/${datadir}/vala-0.22/vapi
	install -m0644 *.vapi ${D}/${datadir}/vala-0.22/vapi
	install -m0644 *.deps ${D}/${datadir}/vala-0.22/vapi
}

FILES_${PN}-dev += "${datadir}"
ALLOW_EMPTY_${PN} = "1"
