/*
 * Copyright (C) 2023 ThePokeCraftMod
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package generations.gg.generations.core.generationscore.rks;

import com.mojang.blaze3d.systems.RenderSystem;
import com.thepokecraftmod.rks.ubo.UniformBlockUploader;
import org.lwjgl.system.MemoryStack;

public class SharedUniformBlock extends UniformBlockUploader {

    public SharedUniformBlock() {
        super(MAT4F_SIZE , 0);
    }

    public void update() {
        try (var stack = MemoryStack.stackPush()) {
            var sharedInfo = stack.nmalloc(MAT4F_SIZE);
            RenderSystem.getProjectionMatrix().getToAddress(sharedInfo);
            upload(0, MAT4F_SIZE, sharedInfo);
        }
    }
}