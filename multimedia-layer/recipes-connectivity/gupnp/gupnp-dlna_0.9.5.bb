#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
SUMMARY = "Helpers for AV applications using DLNA"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://libgupnp-dlna/gupnp-dlna-profile.h;beginline=1;endline=22;md5=1b85459f65cb1e73a885ca137aab6274"

DEPENDS = "libxml2 glib-2.0"

SRC_URI = "http://download.gnome.org/sources/${BPN}/0.9/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "b0c9e5e6edc5ca6fe854bc53560431bf"
SRC_URI[sha256sum] = "44a226300287ffbee1899c35775b4b9153930f29433a75d8c1577d8547c29e83"

inherit autotools pkgconfig

require recipes-connectivity/gupnp/no-vala.inc

FILES_${PN} += "${datadir}/gupnp-dlna-2.0/dlna-profiles"
