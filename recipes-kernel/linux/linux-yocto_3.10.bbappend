#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://lxc-extra.cfg"
SRC_URI += "file://vexpress_a9.cfg"

COMPATIBLE_MACHINE_qemuarmv7a9 = "qemuarmv7a9"
KMACHINE_qemuarmv7a9 = "beagleboard"