#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
# Enable "nice" in busybox .. when updating: make sure to pull recent defconfig and enable nice there!

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "file://defconfig "
