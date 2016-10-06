#
#   Copyright (C) 2016 Pelagicore AB
#   All rights reserved.
#

DESCRIPTION = "The SoftwareContainer framework"
HOMEPAGE = "https://github.com/Pelagicore/softwarecontainer"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"

PR = "r0"
PV = "1.0+git${SRCREV}"

SRC_URI = "git://github.com/Pelagicore/softwarecontainer.git;protocol=https;branch=master"
SRCREV = "bbeb1165b47290bd8e2e6dce33048581337b3fc0"

DEPENDS = "ivi-logging glibmm dbus-c++ lxc jansson"
RDEPENDS_${PN} = "iproute2 iptables bridge-utils"

inherit cmake systemd pkgconfig

S = "${WORKDIR}/git/"

PACKAGECONFIG[pulsegateway] = "-DENABLE_PULSEGATEWAY=ON,-DENABLE_PULSEGATEWAY=OFF,pulseaudio"
PACKAGECONFIG[networkgateway] = "-DENABLE_NETWORKGATEWAY=ON,-DENABLE_NETWORKGATEWAY=OFF"
PACKAGECONFIG[devicenodegateway] = "-DENABLE_DEVICENODEGATEWAY=ON,-DENABLE_DEVICENODEGATEWAY=OFF"
PACKAGECONFIG[dbusgateway] = "-DENABLE_DBUSGATEWAY=ON,-DENABLE_DBUSGATEWAY=OFF"
PACKAGECONFIG[cgroupsgateway] = "-DENABLE_CGROUPSGATEWAY=ON,-DENABLE_CGROUPSGATEWAY=OFF"
PACKAGECONFIG ?= "networkgateway devicenodegateway dbusgateway cgroupsgateway"

EXTRA_OECMAKE += "-DENABLE_TEST=OFF -DENABLE_EXAMPLES=OFF"

do_install_append() {
    mkdir -p ${D}${sysconfdir}/dbus-1/system.d/
    mv ${D}${datadir}/dbus-1/system.d/softwarecontainer-agent.conf ${D}${sysconfdir}/dbus-1/system.d/
}

SYSTEMD_SERVICE_${PN} = "softwarecontainer-agent.service"

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg ${PN}-doc ${PN}-locale"

FILES_${PN} += " \
    ${libdir}/libsoftwarecontainercommon.so \
    ${systemd_unitdir}/system \
    ${sysconfdir}/dbus-1 \
"

INSANE_SKIP_${PN} += "useless-rpaths"
INSANE_SKIP_${PN}-dev += "useless-rpaths"
