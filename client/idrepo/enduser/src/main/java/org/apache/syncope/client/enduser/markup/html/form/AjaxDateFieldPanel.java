/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.syncope.client.enduser.markup.html.form;

import org.apache.syncope.client.ui.commons.markup.html.form.FieldPanel;
import com.googlecode.wicket.kendo.ui.form.datetime.AjaxDatePicker;
import java.util.Date;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.syncope.client.enduser.SyncopeEnduserSession;
import org.apache.syncope.client.ui.commons.markup.html.form.DateFieldPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class AjaxDateFieldPanel extends DateFieldPanel {

    private static final long serialVersionUID = 1919852712185883648L;

    public AjaxDateFieldPanel(final String id, final String name, final IModel<Date> model, final String datePattern) {
        super(id, name, model, datePattern);

        field = new AjaxDatePicker("field", model, AjaxDateFieldPanel.this.getLocale(), datePattern);
        add(field.setLabel(new Model<>(name)).setOutputMarkupId(true));
    }

    @Override
    public FieldPanel<Date> clone() {
        FieldPanel<Date> panel = new AjaxDateFieldPanel(getId(), name, new Model<>(), fmt.getPattern());
        panel.setRequired(isRequired());
        panel.setReadOnly(isReadOnly());
        panel.setTitle(title);

        if (isRequiredLabelAdded) {
            panel.addRequiredLabel();
        }

        return panel;
    }

    @Override
    protected FastDateFormat getDateFormat(final String datePattern) {
        return datePattern == null
                ? SyncopeEnduserSession.get().getDateFormat()
                : FastDateFormat.getInstance(datePattern);
    }
}