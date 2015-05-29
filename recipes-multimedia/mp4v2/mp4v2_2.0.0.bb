SUMMARY = "The MP4v2 library provides an API to create and modify mp4 files as defined by ISO-IEC:14496-1:2001 MPEG-4 Systems"
HOMEPAGE = "https://code.google.com/p/mp4v2/"
SECTION = "libs/multimedia"
LICENSE = "MPL-1.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb3014b036b6d2151d944aef6a84c36f"

DEPENDS = ""

PV = "2.0.0"
SRC_URI = "https://mp4v2.googlecode.com/files/mp4v2-2.0.0.tar.bz2"
SRC_URI[md5sum] = "c91f06711225b34b4c192c9114887b14"
SRC_URI[sha256sum] = "0319b9a60b667cf10ee0ec7505eb7bdc0a2e21ca7a93db96ec5bd758e3428338"

inherit autotools

# Building of man pages fail for some reason - so we disable them. Also, 
# since this is a tarball, some of the svn stuff used to find out versions 
# fail aswell when running autoreconf, so we just put PV in there.
do_configure_prepend() {
    sed -i -e 's/m4_format.*\[\%s-r\%d\].*)))/${PV}))/' ${S}/configure.ac
    sed -i -e 's/\[ADD_MANS\],\[.*\])/[ADD_MANS],[false])/' ${S}/configure.ac
}

