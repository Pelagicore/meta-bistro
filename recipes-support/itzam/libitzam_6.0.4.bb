#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
DESCRIPTION = "libitzam"
HOMEPAGE = "http://coyotegulch.scottrobertladd.net/products/itzam/c/index.html"
LICENSE = "CLOSED"

inherit autotools pkgconfig

SRC_URI = " \
	http://coyotegulch.scottrobertladd.net/products/itzam/c/${PN}-${PV}.tar.gz;name=tarball \
	file://libitzam_install.patch \
	file://pcl.patch \
	"
SRC_URI[tarball.md5sum] = "333f680c21037e32b4000933eae27721"
SRC_URI[tarball.sha256sum] = "d5b565a9d96275e3bf39ea2dd0b121a92b578819650a09fc5add4f4113df734d"

S = "${WORKDIR}/${PN}-${PV}/"

do_configure_prepend() {
	touch AUTHORS COPYING INSTALL NEWS README
	mkdir -p m4
}

