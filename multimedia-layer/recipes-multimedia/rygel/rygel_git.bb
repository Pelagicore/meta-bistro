#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
require rygel-git.inc
LIBV = "2.4"

SRC_URI += " \
	file://0001-Disable-gobject-introspection-to-enable-build-in-Yoc.patch \
	file://0002-Disable-doc-fails-to-build.patch \
	file://no-default-media-engine.patch \
"

DEPENDS += "gupnp-vapi"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"
