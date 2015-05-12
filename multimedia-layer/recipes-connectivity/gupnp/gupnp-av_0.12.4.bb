#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
require recipes-connectivity/gupnp/gupnp-av.inc

SRC_URI = "http://download.gnome.org/sources/${BPN}/0.12/${BPN}-${PV}.tar.xz"

SRC_URI[md5sum] = "f9ba9856af525a910529acf4a85eec33"
SRC_URI[sha256sum] = "548a9cef8ab3007734e20a4ce284c422ae299b7e024a4824299f6ae7e3dd7a5b"

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://libgupnp-av/gupnp-av.h;beginline=1;endline=22;md5=2b47b7b5f799d2ebabe62b895e848820"

