/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.servlet.taglib.ui;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;

import java.util.Locale;

/**
 * @author Sergio González
 */
@OSGiBeanProperties(property = {"service.ranking:Integer=40"})
public class SiteAnalyticsFormNavigatorEntry
	extends BaseSiteFormNavigatorEntry {

	@Override
	public String getCategoryKey() {
		return "advanced";
	}

	@Override
	public String getKey() {
		return "analytics";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "analytics");
	}

	@Override
	public boolean isVisible(User user, Group group) {
		if ((group == null) || group.isCompany()) {
			return false;
		}

		String[] analyticsTypes = PrefsPropsUtil.getStringArray(
			group.getCompanyId(), PropsKeys.ADMIN_ANALYTICS_TYPES,
			StringPool.NEW_LINE);

		if (analyticsTypes.length == 0) {
			return false;
		}

		return true;
	}

	@Override
	protected String getJspPath() {
		return "/html/portlet/sites_admin/site/analytics.jsp";
	}

}