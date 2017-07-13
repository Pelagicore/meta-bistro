#
#   Copyright (C) 2015 Pelagicore AB
#
#   SPDX-License-Identifier: MIT
#
DESCRIPTION = "Additional systemd units for user-sessions"
HOMEPAGE = "https://github.com/sofar/user-session-units"
LICENSE = "LGPLv2"

DEPENDS = "systemd dbus"
PR = "r1"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
SRC_URI = " \
	git://github.com/sofar/user-session-units.git;protocol=https \
	file://dbus-socket-run-dir.patch \
	file://light.target \
	"
SRCREV = "1362ff6d321aa830f381192cd1bce096d400df36"
S = "${WORKDIR}/git/"

inherit autotools pkgconfig systemd

do_install_append() {
	# Remove these files as not needed right now.
	rm -rf ${D}/lib

	# Install a default target for users
	install -m 644 ${WORKDIR}/light.target ${D}/${libdir}/systemd/user/light.target
	mkdir -p ${D}/${sysconfdir}/systemd/user
	ln -sf ${libdir}/systemd/user/light.target ${D}/${sysconfdir}/systemd/user/default.target
}

FILES_${PN} += "${libdir}/systemd/*"
