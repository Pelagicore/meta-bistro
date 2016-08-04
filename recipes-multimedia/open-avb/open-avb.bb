#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "open-avb"
HOMEPAGE = "https://github.com/AVnu/Open-AVB"
LICENSE = "CLOSED"

DEPENDS += "libpcap"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = " \
    gitsm://github.com/AVnu/Open-AVB.git;branch=master;protocol=https \
        file://open-avb-build.patch \
    "

SRCREV = "6958b6cb9b8e774e3c5dff0554b968c10a2a7ac0"

S = "${WORKDIR}/git"

do_compile() {
    mkdir -p ${S}/daemons/maap/build
    oe_runmake daemons_all
#    oe_runmake examples_all 
}

do_install() {
    mkdir -p ${D}/${bindir}/
    install ${S}/daemons/maap/build/maap_daemon ${D}/${bindir}
    install ${S}/daemons/mrpd/mrpd ${D}/${bindir}
    install ${S}/daemons/mrpd/mrpctl ${D}/${bindir}
    install ${S}/daemons/gptp/linux/build/obj/daemon_cl ${D}/${bindir}

}

FILES_${PN} =+ " \
    ${bindir}/* \
"
