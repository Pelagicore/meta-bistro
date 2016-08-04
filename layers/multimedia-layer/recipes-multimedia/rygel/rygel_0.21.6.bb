#
#   Copyright (C) 2015 Pelagicore AB
#
require rygel.inc

DEPENDS += "gupnp-vapi"
LIC_FILES_CHKSUM = "file://COPYING;md5=5f30f0716dfdd0d91eb439ebec522ec2"

SRC_URI += " \
	file://0001-plugins-Add-LightMediascanner-plugin.patch \
	file://no-default-media-engine.patch \
	"

SRC_URI[archive.md5sum] = "3538ea9b1e5256b9141d5a92471525c5"
SRC_URI[archive.sha256sum] = "208bf46b7116bfd06159bf09f6c85f953257874751e3949865587fe5994164b1"
