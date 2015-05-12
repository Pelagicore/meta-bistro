#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Fix systemd-journald seg fault
# See http://bugs.genivi.org/show_bug.cgi?id=201

SRC_URI += " \
	file://0001-systemd-add-lresolv-to-build-with-eglibc.patch \
	"
