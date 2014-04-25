/*
 * This file is part of BarAPI (1) (me.confuser.barapi).
 *
 * BarAPI (1) (me.confuser.barapi) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BarAPI (1) (me.confuser.barapi) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BarAPI (1) (me.confuser.barapi).  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dsh105.barapi.reflection.utility;

import com.dsh105.barapi.reflection.MethodAccessor;
import com.dsh105.barapi.reflection.SafeMethod;
import com.google.common.base.Preconditions;
import org.bukkit.entity.Entity;

public class CommonMethods {

    private CommonMethods() {
        super();
    }

    public static Object getVanillaObject(Entity entity) {
        Preconditions.checkNotNull(entity);

        MethodAccessor<Object> getHandle = new SafeMethod<Object>(CommonReflection.getCraftEntityClass(), "getHandle");

        return getHandle.invoke(entity);
    }
}
