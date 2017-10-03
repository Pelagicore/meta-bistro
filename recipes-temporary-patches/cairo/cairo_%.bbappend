
# QA issues with installed but not shipped files
# This is fixed in meta-ivi but not in 13.0 branch (pyro)

do_install_append() {
   rm ${D}${libdir}/cairo/libcairo-trace.la
   rmdir ${D}${libdir}/cairo/
}
