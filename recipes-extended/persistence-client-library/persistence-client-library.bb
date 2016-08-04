#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = "GENIVI Persistence Client Library"
LICENSE = "MPLv2"
LIC_FILES_CHKSUM = " \
	file://COPYING;md5=6161c6840f21a000e9b52af81d2ca823 \
	"
HOMEPAGE = "http://projects.genivi.org/persistence-client-library/"

PV = "0.7.0+git${SRCREV}"

DEPENDS = "libitzam dbus dlt-daemon"

inherit autotools pkgconfig

SRC_URI = " \
	git://git.projects.genivi.org/persistence/persistence-client-library.git;protocol=git \
	"
SRCREV = "883ee527415d6e49e202a8e0532266db46530bbf"

S = "${WORKDIR}/git"

# Disable parallel make to see if we get rid of an intermittent build error
PARALLEL_MAKE = "-j 1"
