//
//  SuspendWrapper.swift
//  TODOList-iOS
//
//  Created by Jin on 2023/9/7.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import data

func suspend<T>(_ wrapper: SuspendWrapper<T>) async throws -> T {
    return try await withTaskCancellationHandler {
        @MainActor in try await wrapper.execute()
    } onCancel: {
        wrapper.cancel()
    }
}
