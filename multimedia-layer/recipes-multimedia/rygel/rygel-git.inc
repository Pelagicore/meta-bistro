LICENSE = "LGPLv2+"

inherit gnome vala systemd

SRC_URI = " \
	git://github.com/Pelagicore/Media-Manager-Rygel-patched.git;protocol=https \
	file://rygel.service \
	"

SRCREV = "d3e8164dee93d7b59604f3c014c3c1e8ded41c38"
PV = "0.23.3+mm-git${SRCPV}"
S = "${WORKDIR}/git/"

DESCRIPTION = "Collection of DLNA[1] (UPnP[2] AV) devices, implemented through a plug-in mechanism."
SECTION = "network/multimedia"
DEPENDS = "glib-2.0 gupnp gupnp-av gupnp-dlna gstreamer gst-plugins-base sqlite3 libsoup-2.4 libgee shared-mime-info libmediaart"

require rygel-plugins.inc
PACKAGES_DYNAMIC = "${PN}-*"

HOMEPAGE = "http://live.gnome.org/Rygel"

EXTRA_OECONF = "--disable-tracker-plugin --with-media-engine=simple"

FILES_${PN} += " \
	${datadir}/dbus-1/ \
	/lib/systemd \
	"
SYSTEMD_SERVICE_${PN} = "rygel.service"
SYSTEMD_AUTO_ENABLE = "enable"

# Don't ship icons or .desktop files
# Ship systemd service
do_install_append() {
	rm -rf ${D}/usr/share/icons/
	rm -rf ${D}/usr/share/applications
	install -Dm0644 ${WORKDIR}/rygel.service ${D}/lib/systemd/system/rygel.service
}

