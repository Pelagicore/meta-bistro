#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://systemd-udev-use-external-blkid.patch"
RDEPENDS_udev += "util-linux-blkid"

PACKAGECONFIG += " networkd resolved "
