# Copyright (C) 2019 Luxoft Sweden AB
# Released under the MIT license (see LICENSE for the terms)

DESCRIPTION = "Command line tool to Read/Write to PCIe BARx memory space"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8264535c0c4e9c6c335635c4026a8022"

DEPENDS = "readline"

PV="1.0.0+git${SRCPV}"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/pelagicore/pcie_debug;branch=master;protocol=https"

LDFLAGS = "-lreadline"
SRCREV = "cc94f4ee5e6828c44495c131adf918590794c958"

do_install() {
    install -D -m 0755 ${S}/pci_debug ${D}${bindir}/pci_debug
}
