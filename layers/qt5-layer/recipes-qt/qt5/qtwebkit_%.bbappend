#
#   Copyright (C) 2015 Pelagicore AB
#
DEPENDS += "chrpath-replacement-native"
EXTRANATIVEPATH += "chrpath-native"

do_install_append() {
	# Remove rpath from the offending binaries
	chrpath -d ${D}${OE_QMAKE_PATH_LIBEXECS}/QtWebProcess
}
