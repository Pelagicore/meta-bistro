#
#   Copyright (C) 2015 Pelagicore AB
#
DESCRIPTION = " Python module to handle audio metadata"
HOMEPAGE = "http://code.google.com/p/quodlibet/wiki/Development/Mutagen"
SECTION = "devel/python"
LICENSE = "GPL"
PR = "0"

SRC_URI = "https://mutagen.googlecode.com/files/mutagen-${PV}.tar.gz"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit distutils

SRC_URI[md5sum] = "7491af5d04706b1f60fcab71aadfd27f"
SRC_URI[sha256sum] = "4dd30af3a291c0a152838f2bbf1d592bf6ede752b11a159cbf84e75815bcc2b5"

