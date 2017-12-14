#
# Fixing the problem of nothing PROVIDES libasound-module-bluez
# Remove when https://patchwork.openembedded.org/patch/146651/ has been merged
#

RDEPENDS_bluez5_remove = "libasound-module-bluez"
RDEPENDS_bluez4_append = "${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'libasound-module-bluez', '', d)}"
