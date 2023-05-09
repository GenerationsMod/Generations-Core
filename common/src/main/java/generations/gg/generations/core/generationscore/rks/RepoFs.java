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

import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarFile;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RepoFs implements Closeable {
    private final TarFile tarFile;
    protected final Map<ResourceLocation, TarArchiveEntry> entries = new HashMap<>();

    public RepoFs(String namespace, TarFile tarFile) {
        this.tarFile = tarFile;

        for (var entry : tarFile.getEntries()) {
            entries.put(new ResourceLocation(namespace, entry.getName()), entry);
        }
    }

    public byte[] getResource(ResourceLocation id) {
        try {
            return tarFile.getInputStream(entries.get(id)).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read resource " + id, e);
        }
    }

    @Override
    public void close() throws IOException {
        tarFile.close();
    }
}