#
#   Copyright (C) 2015 Pelagicore AB
#   All rights reserved.
#
require recipes-devtools/libgee/libgee.inc
PE = "1"
PR = "${INC_PR}.2"
DEPENDS += "gobject-introspection-stub"
DEPENDS_virtclass-native += "gobject-introspection-stub-native"

SHRT_VER = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/libgee/${SHRT_VER}/${BP}.tar.xz"
SRC_URI[md5sum] = "922ff20952cdef429d3dab0810761342"
SRC_URI[sha256sum] = "0d651cf16d0825961f3f6992a4e27e67fbeb8599d4fcbcbb0f44aa2f7118a389"

