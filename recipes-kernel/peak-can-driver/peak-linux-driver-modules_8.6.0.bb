#
#   Copyright (C) 2015 Pelagicore AB
#   Copyright (C) 2018 Luxoft Sweden AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "PEAK CAN Bus Driver for Linux"
HOMEPAGE = "http://www.peak-system.com/fileadmin/media/linux/index.htm"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/peak-linux-driver-${PV}/Documentation/COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit module

SRC_URI = "http://www.peak-system.com/fileadmin/media/linux/files/peak-linux-driver-${PV}.tar.gz"
SRC_URI[md5sum] = "f62d94c721aa6a83316fcb4232631a64"
SRC_URI[sha256sum] = "62fa0ebde620a436816546bc1f82feb663a32f20db26e8889b7204b9686f5776"

DEPENDS = "virtual/kernel"

S = "${WORKDIR}/peak-linux-driver-${PV}/driver"

EXTRA_OEMAKE = "NET=NETDEV_SUPPORT \
                KERNEL_LOCATION=${STAGING_KERNEL_BUILDDIR} \
"

do_install() {
    # create directories
    install -m 0755 -d ${D}/${base_bindir}
    install -m 0755 -d ${D}/${sysconfdir}/udev/rules.d/
    install -m 0755 -d ${D}/${sysconfdir}/modprobe.d/
    install -m 0755 -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/misc/

    # install kernel module
    install -m 0644 ${S}/pcan.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/misc/
    install -m 0644 ${S}/udev/blacklist-peak.conf ${D}/${sysconfdir}/modprobe.d/pcan.conf

    # install tools and scripts
    install -m 0755 ${S}/lspcan ${D}/${base_bindir}
    install -m 0744 ${S}/pcan_make_devices ${D}/${base_bindir}
    install -m 0744 ${S}/udev/pcan_usb_minor_check.bash ${D}/${base_bindir}

    # install udev rules
    install -m 0644 ${S}/udev/45-pcan.rules ${D}/${sysconfdir}/udev/rules.d/
}

FILES_${PN} += "\
    ${base_bindir} \
    ${sysconfdir}/udev/rules.d/ \
    /lib/modules/${KERNEL_VERSION}/kernel/misc/ \
"

KERNEL_MODULE_AUTOLOAD_append_corei7-64-intel-common = " pcan"
