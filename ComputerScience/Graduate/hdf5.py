import h5py

file_path = "./LoDoPaB-CT/observation_train/observation_train_000.hdf5"  # 替换成实际路径

with h5py.File(file_path, "r") as f:
    print("文件中的键：", list(f.keys()))
    for key in f.keys():
        print(f"{key}: 形状={f[key].shape}, 数据类型={f[key].dtype}")