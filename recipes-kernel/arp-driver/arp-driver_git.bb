#
#   Coprright (C) 2019 Luxoft Sweden AB
#   SPDX-License-Identifier: MIT
#

SUMMARY = "ARP driver kernel module"
DESCRIPTION = "Automotive Reference Platform Kernel module"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "598b325c4ff70208f0524622f612ad642e58f3e5"

SRC_URI = " \
    git://git@github.com/pelagicore/arp-driver.git;protocol=https;branch=master; \
    file://arp.conf \
"

S = "${WORKDIR}/git"

inherit module

do_install_append() {
    install -Dm 0644 ${WORKDIR}/arp.conf ${D}${sysconfdir}/modprobe.d/arp.conf
}

KERNEL_MODULE_AUTOLOAD += "arp arp-camera"
