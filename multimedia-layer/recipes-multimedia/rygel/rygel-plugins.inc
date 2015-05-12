LIBV = "2.2"

python populate_packages_prepend () {
    rygel_enginedir = d.expand('${libdir}/rygel-${LIBV}/engines')
    rygel_plugindir = d.expand('${libdir}/rygel-${LIBV}/plugins')
    rygel_enginedbgdir = d.expand(rygel_enginedir + '/.debug')
    rygel_plugindbgdir = d.expand(rygel_plugindir + '/.debug')
    postinst = d.getVar('plugin_postinst', True)

    do_split_packages(d, rygel_enginedir, 'librygel(.*)\.so$', d.expand('${PN}-plugin%s'), 'Rygel plugin for %s', postinst=postinst, extra_depends=d.expand('${PN}'))
    do_split_packages(d, rygel_enginedir, '(.*)\.plugin$', d.expand('${PN}-plugin-%s'), 'Rygel plugin for %s', postinst=postinst, extra_depends=d.expand('${PN}'))
    do_split_packages(d, rygel_enginedir, 'librygel(.*)\.la$', d.expand('${PN}-plugin%s-dev'), 'Rygel plugin for %s (development files)', extra_depends=d.expand('${PN}-dev'))
    do_split_packages(d, rygel_enginedir, 'librygel(.*)\.a$', d.expand('${PN}-plugin%s-staticdev'), 'Rygel plugin for %s (static development files)', extra_depends=d.expand('${PN}-staticdev'))
    do_split_packages(d, rygel_plugindir, 'librygel(.*)\.so$', d.expand('${PN}-plugin%s'), 'Rygel plugin for %s', postinst=postinst, extra_depends=d.expand('${PN}'))
    do_split_packages(d, rygel_plugindir, '(.*)\.plugin$', d.expand('${PN}-plugin-%s'), 'Rygel plugin for %s', postinst=postinst, extra_depends=d.expand('${PN}'))
    do_split_packages(d, rygel_plugindir, 'librygel(.*)\.la$', d.expand('${PN}-plugin%s-dev'), 'Rygel plugin for %s (development files)', extra_depends=d.expand('${PN}-dev'))
    do_split_packages(d, rygel_plugindir, 'librygel(.*)\.a$', d.expand('${PN}-plugin%s-staticdev'), 'Rygel plugin for %s (static development files)', extra_depends=d.expand('${PN}-staticdev'))
    do_split_packages(d, rygel_enginedbgdir, 'librygel(.*)\.so$', d.expand('${PN}-plugin%s-dbg'), 'Rygel plugin for %s (debugging symbols)', extra_depends=d.expand('${PN}-dbg'))
    do_split_packages(d, rygel_plugindbgdir, 'librygel(.*)\.so$', d.expand('${PN}-plugin%s-dbg'), 'Rygel plugin for %s (debugging symbols)', extra_depends=d.expand('${PN}-dbg'))

    pn = d.getVar('PN', True)
    metapkg =  pn + '-meta'
    d.setVar('ALLOW_EMPTY_' + metapkg, "1")
    d.setVar('FILES_' + metapkg, "")
    blacklist = [ pn + '-locale', pn + '-dev', pn + '-dbg', pn + '-doc' ]
    metapkg_rdepends = []
    packages = d.getVar('PACKAGES', True).split()
    for pkg in packages[1:]:
        if not pkg in blacklist and not pkg in metapkg_rdepends and not pkg.endswith('-dev') and not pkg.endswith('-dbg') and not pkg.count('locale') and not pkg.count('-staticdev'):
            metapkg_rdepends.append(pkg)
    d.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends))
    d.setVar('DESCRIPTION_' + metapkg, pn + ' meta package')
}

PACKAGES += "${PN}-meta"
