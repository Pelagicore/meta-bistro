LICENSE = "LGPLv2+"

inherit gnome vala systemd

SRC_URI = " \
	${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive \
	file://rygel.service \
	"

DESCRIPTION = "Collection of DLNA[1] (UPnP[2] AV) devices, implemented through a plug-in mechanism."
SECTION = "network/multimedia"
DEPENDS = "glib-2.0 gupnp gupnp-av gupnp-dlna gstreamer gst-plugins-base sqlite3 libsoup-2.4 libgee shared-mime-info"

require rygel-plugins.inc
PACKAGES_DYNAMIC = "${PN}-*"

HOMEPAGE = "http://live.gnome.org/Rygel"

EXTRA_OECONF = "--disable-tracker-plugin --with-media-engine=simple --without-ui"

FILES_${PN} += " \
	${datadir}/dbus-1/ \
	/lib/systemd \
	"
SYSTEMD_SERVICE_${PN} = "rygel.service"
SYSTEMD_AUTO_ENABLE = "disable"

# Don't ship icons or .desktop files
# Ship systemd service
do_install_append() {
	rm -rf ${D}/usr/share/icons/
	rm -rf ${D}/usr/share/applications
	install -Dm0644 ${WORKDIR}/rygel.service ${D}/lib/systemd/system/rygel.service
}
