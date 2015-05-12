#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-systemd-unit-type-should-be-in-lowercase-so-use-Type.patch"
