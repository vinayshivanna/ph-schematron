/**
 * Copyright (C) 2014 phloc systems (www.phloc.com)
 * Copyright (C) 2014 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.schematron.pure.model;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.log.InMemoryLogger;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.impl.MicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.schematron.CSchematron;
import com.helger.schematron.CSchematronXML;

/**
 * A single Schematron param-element.<br>
 * A name-value pair providing parameters for an abstract pattern. The required
 * name attribute is an XML name with no colon. The required value attribute is
 * a fragment of a query.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class PSParam implements IPSElement
{
  private String m_sName;
  private String m_sValue;

  public PSParam ()
  {}

  public boolean isValid (@Nonnull final InMemoryLogger aLogger)
  {
    if (StringHelper.hasNoText (m_sName))
    {
      aLogger.error ("<param> has no 'name'");
      return false;
    }
    if (StringHelper.hasNoText (m_sValue))
    {
      aLogger.error ("<param> has no 'value'");
      return false;
    }
    return true;
  }

  public boolean isMinimal ()
  {
    return false;
  }

  public void setName (@Nullable final String sName)
  {
    m_sName = sName;
  }

  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  public void setValue (@Nullable final String sValue)
  {
    if (sValue != null && sValue.length () == 0)
      throw new IllegalArgumentException ("value may not be empty!");
    m_sValue = sValue;
  }

  @Nullable
  public String getValue ()
  {
    return m_sValue;
  }

  @Nonnull
  public IMicroElement getAsMicroElement ()
  {
    final IMicroElement ret = new MicroElement (CSchematron.NAMESPACE_SCHEMATRON, CSchematronXML.ELEMENT_PARAM);
    ret.setAttribute (CSchematronXML.ATTR_NAME, m_sName);
    ret.setAttribute (CSchematronXML.ATTR_VALUE, m_sValue);
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("name", m_sName).append ("value", m_sValue).toString ();
  }
}