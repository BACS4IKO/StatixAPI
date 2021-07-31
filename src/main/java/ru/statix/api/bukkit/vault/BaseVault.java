package ru.statix.api.bukkit.vault;
/**
 * @ItzStatix:
 * Вроде весьма простой класс, но чуть сократит ваш код
 */
public class BaseVault {

    public String getPrefix(String name) {
        return new VaultManager().getVaultPlayer(name).getPrefix();
    }
    public String getGroupPrefix(String name) {
        return new VaultManager().getVaultPlayer(name).getGroupPrefix();
    }
    public String getGroupSuffix(String name) {
        return new VaultManager().getVaultPlayer(name).getGroupSuffix();
    }
    public String getSuffix(String name) {
        return new VaultManager().getVaultPlayer(name).getSuffix();
    }
    public int getBalance(String name) {
        return (int) new VaultManager().getVaultPlayer(name).getBalance();
    }
    public void setBalance(String name, int i) {
        new VaultManager().getVaultPlayer(name).setBalance(i);
    }
    public void giveMoney(String name, int i) {
        new VaultManager().getVaultPlayer(name).giveMoney(i);
    }
    public void takeMoney(String name, int i) {
        new VaultManager().getVaultPlayer(name).takeMoney(i);
    }

    public boolean hasGroup(String name, String group) {
        return new VaultManager().getVaultPlayer(name).hasGroup(group);
    }
    public boolean hasPerm(String name, String perm) {
        return new VaultManager().getVaultPlayer(name).hasPermission(perm);
    }

    /**
     * А вот тут мы упрощаем и так весьма простую вещь, если игрок имеет в балансе указанное число, то
     * мы возвращаем положительное значение, если же нет, то отрицательное.
     */
    public boolean hasBalance(String name, int i) {
        if (getBalance(name) >= i) {
            return true;
        } else {
            return false;
        }
    }

    public void removePerm(String name, String perm) {
        new VaultManager().getVaultPlayer(name).removePermission(perm);
    }
    public void removeGroup(String name, String group) {
        new VaultManager().getVaultPlayer(name).removeGroup(group);
    }
    public void addPerm(String name, String perm) {
        new VaultManager().getVaultPlayer(name).addPermission(perm);
    }
    public void addGroup(String name, String group) {
        new VaultManager().getVaultPlayer(name).addGroup(group);
    }
}
