SUMMARY = "Profiling utilities for GStreamer 1.0 pipelines"
HOMEPAGE = "https://github.com/kirushyk/gst-instruments"
DEPENDS = "gstreamer1.0"

LICENSE = "LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6a600fd5e1d9cbde2d983680233ad02"

inherit autotools pkgconfig

PV = "0.2.1+git${SRCPV}"
SRC_URI = "git://github.com/kirushyk/gst-instruments.git;protocol=https"
SRCREV = "55c305035242292b79607a4bc113440f4fb72efb"

S = "${WORKDIR}/git"
