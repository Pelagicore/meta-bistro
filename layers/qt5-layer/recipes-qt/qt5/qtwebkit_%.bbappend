#
#   Copyright (C) 2015 Pelagicore AB
#
DEPENDS += "chrpath-replacement-native"
EXTRANATIVEPATH += "chrpath-native"

# Temporary fix
# Remove qtlocation as this pulls in qtjsondb which seems to break qtpim
DEPENDS := "${@oe_filter_out('qtlocation', '${DEPENDS}', d)}"

do_install_append() {
	# Remove rpath from the offending binaries
	chrpath -d ${D}${OE_QMAKE_PATH_LIBEXECS}/QtWebProcess
}
